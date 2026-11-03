
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Test_Motor_Seconds")
public class encoder extends LinearOpMode {

    private DcMotor motor;

    @Override
    public void runOpMode() {

        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");

        // 1. Set motor direction
        motor.setDirection(DcMotor.Direction.FORWARD);

        // 2. Set zero power behavior
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 3. Reset encoder
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // 4. Set target encoder position
        motor.setTargetPosition(1000);

        // 5. Set motor mode
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Target Position", motor.getTargetPosition());
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {

            // 6. Set motor power
            motor.setPower(0.5);

            // 7. Monitor motor until target is reached
            while (opModeIsActive() && motor.isBusy()) {

                telemetry.addData("Motor Power", motor.getPower());
                telemetry.addData("Current Position", motor.getCurrentPosition());
                telemetry.addData("Target Position", motor.getTargetPosition());
                telemetry.addData("Motor Direction", motor.getDirection());
                telemetry.addData("Motor Mode", motor.getMode());
                telemetry.addData("Zero Power Behavior", motor.getZeroPowerBehavior());
                telemetry.addData("Is Busy", motor.isBusy());
                telemetry.addData("Is Busy", motor.getVelocity());

                telemetry.update();
            }

            // 8. Stop motor
            motor.setPower(0);

            telemetry.addData("Status", "Target Reached");
            telemetry.addData("Final Position", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
