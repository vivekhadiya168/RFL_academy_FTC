
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@TeleOp(name = "PID_Baseline_Test")
public class pid_test extends LinearOpMode {

    @Override
    public void runOpMode() {

        DcMotorEx motor = hardwareMap.get(DcMotorEx.class, "motor");
        ElapsedTime timer = new ElapsedTime();

        int[] targets = {500, 1000, 2000, 1000, 1000};
        double[] powers = {0.2, 0.2, 0.2, 0.5, 1.0};

        motor.setDirection(DcMotor.Direction.FORWARD);

        motor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_USING_ENCODER,
                new PIDFCoefficients(5, 2, 0, 0)
        );

        waitForStart();

        for (int i = 0; i < targets.length && opModeIsActive(); i++) {

            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setTargetPosition(targets[i]);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            timer.reset();
            motor.setPower(powers[i]);

            while (opModeIsActive() && motor.isBusy()) {
                telemetry.addData("Test", i + 1);
                telemetry.addData("Target", targets[i]);
                telemetry.addData("Power", powers[i]);
                telemetry.addData("Current Ticks", motor.getCurrentPosition());
                telemetry.addData("Time", "%.2f sec", timer.seconds());
                telemetry.update();
            }

            motor.setPower(0);

            telemetry.addData("Test", i + 1);
            telemetry.addData("Final Position", motor.getCurrentPosition());
            telemetry.addData("Target", targets[i]);
            telemetry.addData("Time Taken", "%.2f sec", timer.seconds());
            telemetry.addLine("Observe overshoot and shaking");
            telemetry.update();

            sleep(3000);
        }

        motor.setPower(0);
    }
}
