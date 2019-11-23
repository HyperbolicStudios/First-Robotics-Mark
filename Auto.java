// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Drive Forward", group="Exercises")
//@Disabled
public class Auto extends LinearOpMode
{
    private DcMotor motorLeft;
    private DcMotor motorRight;


    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.get(DcMotor.class, "left");
        motorRight = hardwareMap.get(DcMotor.class, "right");
        double x = 0;


        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power.
        sleep(20000);
        if (x == 0) {
            motorLeft.setPower(1);
            motorRight.setPower(-1);
            x = 1;
        }
        sleep(1800);        // wait for 2 seconds.

        // set motor power to zero to stop motors.

        motorLeft.setPower(0.0);
        motorRight.setPower(0.0);
        sleep(100000);
    }
}
