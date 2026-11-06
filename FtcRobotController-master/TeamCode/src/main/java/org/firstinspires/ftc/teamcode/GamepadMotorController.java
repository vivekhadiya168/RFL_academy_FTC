//Motor initialization
//PIDF configuration
//Four gamepad buttons
//Forward and reverse velocity
//Velocity reach time
//Telemetry


package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class GamepadMotorController {

    // ---------------- MOTOR ----------------

    private DcMotorEx motor;
    private ElapsedTime timer;

    // ---------------- VELOCITY ----------------

    private final double TARGET_VELOCITY = 2000;

    private double actualVelocity;
    private double reachTime = -1;

    // ---------------- PIDF ----------------

    private final double P = 10;
    private final double I = 5;
    private final double D = 0;
    private final double F = 3;

    private PIDFCoefficients defaultPIDF;

    // ---------------- MODE ----------------

    private String currentMode = "STOP";


    // ---------------- CONSTRUCTOR ----------------

    public GamepadMotorController(DcMotorEx motor) {

        this.motor = motor;
        timer = new ElapsedTime();

        initializeMotor();
        saveDefaultPIDF();
    }


    // ---------------- MOTOR INITIALIZATION ----------------

    private void initializeMotor() {

        motor.setDirection(DcMotor.Direction.FORWARD);

        motor.setMode(
                DcMotor.RunMode.RUN_USING_ENCODER
        );
    }


    // ---------------- PIDF CONFIGURATION ----------------

    private void saveDefaultPIDF() {

        defaultPIDF = motor.getPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER
        );
    }

    private void setCustomPIDF() {

        motor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(P, I, D, F)
        );
    }

    private void setDefaultPIDF() {

        motor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                defaultPIDF
        );
    }


    // ---------------- GAMEPAD CONTROL ----------------

    public void update(Gamepad gamepad, Telemetry telemetry) {

        if (gamepad.right_bumper) {

            runMotor("PID FORWARD", TARGET_VELOCITY);

        } else if (gamepad.right_trigger > 0.5) {

            runMotor("DEFAULT FORWARD", TARGET_VELOCITY);

        } else if (gamepad.left_bumper) {

            runMotor("PID REVERSE", -TARGET_VELOCITY);

        } else if (gamepad.left_trigger > 0.5) {

            runMotor("DEFAULT REVERSE", -TARGET_VELOCITY);

        } else {

            stopMotor();
        }

        showTelemetry(telemetry);
    }


    // ---------------- MOTOR VELOCITY CONTROL ----------------

    private void runMotor(String mode, double velocity) {

        if (!mode.equals(currentMode)) {

            timer.reset();
            reachTime = -1;

            currentMode = mode;
        }

        if (mode.startsWith("PID")) {

            setCustomPIDF();

        } else {

            setDefaultPIDF();
        }

        motor.setVelocity(velocity);

        actualVelocity = motor.getVelocity();

        calculateReachTime(Math.abs(velocity));
    }


    // ---------------- VELOCITY REACH TIME ----------------

    private void calculateReachTime(double targetVelocity) {

        if (reachTime < 0 &&
                Math.abs(actualVelocity) >= targetVelocity) {

            reachTime = timer.seconds();
        }
    }


    // ---------------- MOTOR STOP ----------------

    private void stopMotor() {

        if (!currentMode.equals("STOP")) {

            currentMode = "STOP";
            reachTime = -1;
        }

        motor.setVelocity(0);
    }


    // ---------------- TELEMETRY ----------------

    private void showTelemetry(Telemetry telemetry) {

        telemetry.addData("Current Mode", currentMode);
        telemetry.addData("Actual Velocity", actualVelocity);

        telemetry.addData(
                "Time to Reach Target",
                reachTime < 0 ? "Not reached" :
                        String.format("%.3f sec", reachTime)
        );

        telemetry.addData("PID P", P);
        telemetry.addData("PID I", I);
        telemetry.addData("PID D", D);
        telemetry.addData("PID F", F);

        telemetry.update();
    }


    // ---------------- STOP ----------------

    public void stop() {

        motor.setVelocity(0);
    }
}

//Method	                                   Responsibility
//GamepadMotorController()	                   Initialize controller
//initializeMotor()                            Configure motor
//saveDefaultPIDF()	                           Save original PIDF
//setCustomPIDF()	                           Apply P=10, I=5, D=0, F=3
//setDefaultPIDF()	                           Restore original PIDF
//update()	                                   Read gamepad buttons
//runMotor()	                               Execute forward/reverse velocity
//calculateReachTime()	                       Calculate velocity reach time
//stopMotor()	                               Stop motor
//showTelemetry()	                           Display motor information
