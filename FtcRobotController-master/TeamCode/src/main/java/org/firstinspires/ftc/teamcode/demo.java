package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime; // Required import for tracking time

@TeleOp(name = "Test_Motor_Seconds")
public class demo extends LinearOpMode {

    private DcMotor motor;
    private DcMotor motor2;
    private Servo rgb;
    private Servo s1;

    /**
     * Basic motor test: run motorTest forward for 2 seconds, then stop.
     */
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        rgb = hardwareMap.get(Servo.class, "rgb");
        s1 = hardwareMap.get(Servo.class, "s1");

        s1.setPosition(0);
        // Put initialization blocks here.
        waitForStart();

        if (opModeIsActive()) {

            motor.setPower(0.5);
            rgb.setPosition(0.555);
            sleep(2000);
            rgb.setPosition(0);
            motor.setPower(0);
            sleep(2000);
            s1.setPosition(0.5);
            rgb.setPosition(0.333);
            sleep(2000);
            rgb.setPosition(0);


            s1.setPosition(0);
            sleep(2000);

            motor2.setPower(-0.7);
            rgb.setPosition(0.555);
            sleep(2000);
            rgb.setPosition(0);
            motor2.setPower(0);
            sleep(2000);
            s1.setPosition(0.5);
            rgb.setPosition(0.333);
            sleep(2000);

        }
        // Stops the motor after the loop ends
    }
}
