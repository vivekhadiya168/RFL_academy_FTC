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
    private double power1 = 0;
    private double power2 = 0.5;
    private double power3 = 1;
    private double reverse_power = -0.5;




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
                motor1.setPower(power3);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();
            }
            else if (gamepad1.b){
                motor1.setPower(power1);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();
            }
            else if (gamepad1.x){
                s1.setPosition(0.5);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad1.y){
                s1.setPosition(0.7);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad1.left_bumper){
                motor1.setPower(power2);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();

                rgb.setPosition(0.333);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad1.right_bumper){
                motor2.setPower(power2);

                telemetry.addData("initialized", motor2.getPower());
                telemetry.update();

                rgb.setPosition(0.555);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad1.left_trigger_pressed){
                motor1.setPower(power1);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();

                rgb.setPosition(0.0);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad1.right_trigger_pressed){
                motor2.setPower(power1);

                telemetry.addData("initialized", motor2.getPower());
                telemetry.update();

                rgb.setPosition(0.0);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad1.dpad_left){
                s1.setPosition(0.0);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad1.dpad_down){
                s1.setPosition(0.2);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad1.dpad_right){
                s1.setPosition(0.5);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad1.dpad_up){
                s1.setPosition(0.8);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad2.a) {
                s1.setPosition(0.2);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

                rgb.setPosition(0.333);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();
            }
            else if (gamepad2.b){
                s1.setPosition(0.9);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

                rgb.setPosition(0.555);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();
            }
            else if (gamepad2.left_bumper){
                motor1.setPower(reverse_power);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();

                rgb.setPosition(0.333);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad2.right_bumper){
                motor2.setPower(reverse_power);

                telemetry.addData("initialized", motor2.getPower());
                telemetry.update();

                rgb.setPosition(0.555);

                telemetry.addData("initialized", rgb.getPosition());
                telemetry.update();

            }
            else if (gamepad2.left_trigger_pressed){
                motor1.setPower(0.0);

                telemetry.addData("initialized", motor1.getPower());
                telemetry.update();

                rgb.setPosition(0.0);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }
            else if (gamepad2.right_trigger_pressed){
                motor2.setPower(0.0);

                telemetry.addData("initialized", motor2.getPower());
                telemetry.update();

                rgb.setPosition(0.0);

                telemetry.addData("initialized", s1.getPosition());
                telemetry.update();

            }





        }
        // Stops the motor after the loop ends
    }
}
