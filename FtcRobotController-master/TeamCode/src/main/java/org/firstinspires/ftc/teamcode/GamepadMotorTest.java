
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.controllers.GamepadMotorController;


@TeleOp(name = "FTC_Gamepad_Motor_Control")
public class GamepadMotorTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initialize motor
        DcMotorEx motor = hardwareMap.get(
                DcMotorEx.class,
                "motor"
        );

        // Create motor controller
        GamepadMotorController controller =
                new GamepadMotorController(motor);

        telemetry.addLine("Gamepad Motor Controller Ready");
        telemetry.update();

        waitForStart();

        // Gamepad control loop
        while (opModeIsActive()) {

            controller.update(gamepad1, telemetry);
        }

        controller.stop();
    }
}
