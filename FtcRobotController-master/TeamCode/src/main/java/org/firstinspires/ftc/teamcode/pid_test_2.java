
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Velocity_2000_Test")
public class pid_test_2 extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");
        ElapsedTime timer = new ElapsedTime();

        double targetVelocity = 2000;
        double actualVelocity;
        double reachTime = -1;

        // Set motor direction and mode
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set Velocity PIDF values


        // Read updated Velocity PIDF values
        PIDFCoefficients velocityPID =
                motor.getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (opModeIsActive()) {

            timer.reset();
            motor.setVelocity(targetVelocity);

            while (opModeIsActive() && timer.seconds() < 10) {

                actualVelocity = motor.getVelocity();

                // Record time when velocity reaches target
                if (reachTime < 0 && actualVelocity >= targetVelocity) {
                    reachTime = timer.seconds();
                }

                telemetry.addData("Target Velocity", targetVelocity);
                telemetry.addData("Actual Velocity", actualVelocity);
                telemetry.addData("Velocity Error",
                        targetVelocity - actualVelocity);

                telemetry.addData("Elapsed Time",
                        "%.2f sec", timer.seconds());

                telemetry.addData("Time to Reach Target",
                        reachTime < 0 ? "Not reached" :
                                String.format("%.3f sec", reachTime));

                // Display Velocity PIDF
                telemetry.addData("Velocity P", velocityPID.p);
                telemetry.addData("Velocity I", velocityPID.i);
                telemetry.addData("Velocity D", velocityPID.d);
                telemetry.addData("Velocity F", velocityPID.f);

                telemetry.update();
            }

            motor.setVelocity(0);

            telemetry.addLine("Test Completed");
            telemetry.update();
        }
    }
}

