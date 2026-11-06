
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.List;

@TeleOp(name = "Limelight 16h5 Detection")
public class LimelightAprilTag extends LinearOpMode {

    private Limelight3A limelight;

    private static final int PIPELINE = 0;

    @Override
    public void runOpMode() {

        // 1. Initialize Limelight
        limelight = hardwareMap.get(
                Limelight3A.class,
                "limelight"
        );

        // 2. Set data polling rate
        limelight.setPollRateHz(100);

        // 3. Select AprilTag pipeline
        limelight.pipelineSwitch(PIPELINE);

        // 4. Start Limelight
        limelight.start();

        telemetry.addLine("Limelight Initialized");
        telemetry.addLine("AprilTag Family: 16h5");
        telemetry.addData("Pipeline", PIPELINE);
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // 5. Get latest camera result
            LLResult result = limelight.getLatestResult();

            if (result != null &&
                    result.isValid() &&
                    result.getStaleness() < 250 &&
                    result.getPipelineIndex() == PIPELINE) {

                // 6. Get detected AprilTags
                List<LLResultTypes.FiducialResult> tags =
                        result.getFiducialResults();

                if (tags != null && !tags.isEmpty()) {

                    telemetry.addData(
                            "Status", "AprilTag Detected"
                    );

                    telemetry.addData(
                            "Total Tags", tags.size()
                    );

                    // 7. Display every detected ID
                    for (LLResultTypes.FiducialResult tag : tags) {

                        int id = tag.getFiducialId();

                        telemetry.addData(
                                "AprilTag ID", id
                        );
                    }

                } else {

                    telemetry.addLine("No AprilTag Detected");
                }

            } else {

                telemetry.addLine("Waiting for Camera Results");
            }

            telemetry.update();

            sleep(20);
        }

        // 8. Stop Limelight
        limelight.stop();
    }
}
