package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime; // Required import for tracking time

@TeleOp(name = "Test_Motor_Seconds")
public class test_gamepad extends LinearOpMode {

    private DcMotor motor1;
    private DcMotor motor2;
    private Servo rgb;
    private Servo s1;




    /**
     * Basic motor test: run motorTest forward for 2 seconds, then stop.
     */
    @Override
    public void runOpMode() {
        motor1 = hardwareMap.get(DcMotor.class, "motor");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        rgb = hardwareMap.get(Servo.class, "rgb");
        s1 = hardwareMap.get(Servo.class, "s1");

//        s1.setPosition(0);
        // Put initialization blocks here.
        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a) {
                motor1.setPower(1);
            }
            else if (gamepad1.b){
                motor1.setPower(0);
            }
            else if (gamepad1.x){
                s1.setPosition(0.5);

            }
            else if (gamepad1.y){
                s1.setPosition(0.7);

            }
            else if (gamepad1.left_bumper){
                motor1.setPower(0.5);
                rgb.setPosition(0.333);

            }
            else if (gamepad1.right_bumper){
                motor2.setPower(0.5);
                rgb.setPosition(0.555);

            }
            else if (gamepad1.left_trigger_pressed){
                motor1.setPower(0.0);
                rgb.setPosition(0.0);

            }
            else if (gamepad1.right_trigger_pressed){
                motor2.setPower(0.0);
                rgb.setPosition(0.0);

            }
            else if (gamepad1.dpad_left){
                s1.setPosition(0.0);

            }
            else if (gamepad1.dpad_down){
                s1.setPosition(0.2);

            }
            else if (gamepad1.dpad_right){
                s1.setPosition(0.5);

            }
            else if (gamepad1.dpad_up){
                s1.setPosition(0.8);

            }
            else if (gamepad2.a) {
                s1.setPosition(0.2);
                rgb.setPosition(0.333);
            }
            else if (gamepad2.b){
                s1.setPosition(0.9);
                rgb.setPosition(0.555);
            }
            else if (gamepad2.left_bumper){
                motor1.setPower(-0.5);
                rgb.setPosition(0.333);

            }
            else if (gamepad2.right_bumper){
                motor2.setPower(-0.5);
                rgb.setPosition(0.555);

            }
            else if (gamepad2.left_trigger_pressed){
                motor1.setPower(0.0);
                rgb.setPosition(0.0);

            }
            else if (gamepad2.right_trigger_pressed){
                motor2.setPower(0.0);
                rgb.setPosition(0.0);

            }





        }
        // Stops the motor after the loop ends
    }
}
