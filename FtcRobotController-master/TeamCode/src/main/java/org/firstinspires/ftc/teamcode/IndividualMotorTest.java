
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Individual_Motor_Test")
public class IndividualMotorTest extends LinearOpMode {

    private static final double MOTOR_SPEED = 0.5;

    @Override
    public void runOpMode() {

        // Initialize motors
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Motor directions
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // BRAKE behavior
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Encoder mode
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            // FRONT LEFT
            if (gamepad1.dpad_up) {
                frontLeft.setPower(MOTOR_SPEED);
            } else if (gamepad1.dpad_down) {
                frontLeft.setPower(-MOTOR_SPEED);
            } else {
                frontLeft.setPower(0);
            }

            // FRONT RIGHT
            if (gamepad1.y) {
                frontRight.setPower(MOTOR_SPEED);
            } else if (gamepad1.a) {
                frontRight.setPower(-MOTOR_SPEED);
            } else {
                frontRight.setPower(0);
            }

            // BACK LEFT
            if (gamepad1.dpad_right) {
                backLeft.setPower(MOTOR_SPEED);
            } else if (gamepad1.dpad_left) {
                backLeft.setPower(-MOTOR_SPEED);
            } else {
                backLeft.setPower(0);
            }

            // BACK RIGHT
            if (gamepad1.right_bumper) {
                backRight.setPower(MOTOR_SPEED);
            } else if (gamepad1.left_bumper) {
                backRight.setPower(-MOTOR_SPEED);
            } else {
                backRight.setPower(0);
            }

            // Telemetry
            telemetry.addData("Motor Speed", MOTOR_SPEED);
            telemetry.addData("Front Left", frontLeft.getPower());
            telemetry.addData("Front Right", frontRight.getPower());
            telemetry.addData("Back Left", backLeft.getPower());
            telemetry.addData("Back Right", backRight.getPower());
            telemetry.update();
        }
    }
}
