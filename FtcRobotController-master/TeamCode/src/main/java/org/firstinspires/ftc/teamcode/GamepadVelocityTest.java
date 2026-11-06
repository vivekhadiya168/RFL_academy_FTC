
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Gamepad_Velocity_Test")
public class GamepadVelocityTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotorEx motor = hardwareMap.get(
                DcMotorEx.class, "motor"
        );

        ElapsedTime timer = new ElapsedTime();

        double targetVelocity = 0;
        double actualVelocity;
        double reachTime = -1;

        String currentMode = "STOP";

        // Motor configuration
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Set Velocity PIDF
        motor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(10, 5, 0, 3)
        );

        waitForStart();

        while (opModeIsActive()) {

            double newTarget = 0;
            String newMode = "STOP";

            // Y = 2000 velocity
            if (gamepad1.y) {
                newTarget = 2000;
                newMode = "FORWARD WITH VELOCITY";
            }

            // X = 1500 velocity
            else if (gamepad1.x) {
                newTarget = 1500;
                newMode = "FORWARD WITH VELOCITY";
            }

            // A = 1000 velocity
            else if (gamepad1.a) {
                newTarget = 1000;
                newMode = "FORWARD WITH VELOCITY";
            }

            // Reset timer when target changes
            if (newTarget != targetVelocity) {
                targetVelocity = newTarget;
                timer.reset();
                reachTime = -1;
            }

            // Motor velocity control
            motor.setVelocity(targetVelocity);

            // Read actual velocity
            actualVelocity = motor.getVelocity();

            // Record target velocity reach time
            if (targetVelocity > 0 &&
                    reachTime < 0 &&
                    actualVelocity >= targetVelocity) {

                reachTime = timer.seconds();
            }

            // Telemetry
            currentMode = newMode;

            telemetry.addData("Current Mode", currentMode);
            telemetry.addData("Current Velocity", actualVelocity);

            telemetry.addData(
                    "Reach Time",
                    targetVelocity == 0 ? "N/A" :
                            reachTime < 0 ? "Not reached" :
                                    String.format("%.3f sec", reachTime)
            );

            telemetry.update();
        }

        motor.setVelocity(0);
    }
}
