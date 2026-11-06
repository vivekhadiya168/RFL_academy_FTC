
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Max_Velocity_Test")
public class MaxVelocityTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotorEx motor = hardwareMap.get(
                DcMotorEx.class, "motor"
        );

        double actualVelocity = 0;
        double maxVelocity = 0;

        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Max Velocity Test Ready");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // Run motor forward at full power
            motor.setPower(1.0);

            // Read current velocity
            actualVelocity = motor.getVelocity();

            // Update maximum velocity
            if (actualVelocity > maxVelocity) {
                maxVelocity = actualVelocity;
            }

            // Telemetry
            telemetry.addData("Current Mode", "Forward - Power 1.0");
            telemetry.addData("Current Velocity", actualVelocity);
            telemetry.addData("Maximum Velocity Reached", maxVelocity);

            telemetry.update();
        }

        motor.setPower(0);
    }
}
