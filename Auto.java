// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Drive Forward", group="Exercises")
//@Disabled
public class Auto extends LinearOpMode
{
    private DcMotor motorLeft; //Not relevant
    private DcMotor motorRight;
    private DcMotor armA1;
    private DcMotor motorStrafe;
    private Servo LeftFound;
    private Servo RightFound;
    private Servo grab;
    private Servo grab2;
    private Servo grabPivot;
    private DistanceSensor front;

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException
    {
        motorLeft = hardwareMap.get(DcMotor.class, "left");     //Create devices - don't worry about this
        motorRight = hardwareMap.get(DcMotor.class, "right");
        armA1 = hardwareMap.get(DcMotor.class, "ArmA1");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        grab = hardwareMap.get(Servo.class, "Grab");
        grab2 = hardwareMap.get(Servo.class, "Grab2");
        grabPivot = hardwareMap.get(Servo.class, "grabPivot");
        //LeftFound = hardwareMap.get(Servo.class, "LeftFound");
        //RightFound = hardwareMap.get(Servo.class, "LeftFound");
        front = hardwareMap.get(DistanceSensor.class, "front");
        //sensorCRBottom = hardwareMap.get(DistanceSensor.class, "sensorColorRangeFront");
        //sensorCRSide = hardwareMap.get(DistanceSensor.class, "sensorColorRangeFront");
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)


        telemetry.addData("Mode", "waiting");
        telemetry.addData("Front (cm)", front.getDistance(DistanceUnit.CM));
        telemetry.update();

        // wait for start button.

        waitForStart();
        while(opModeIsActive()) {
            if (front.getDistance(DistanceUnit.CM) > 15 && front.getDistance(DistanceUnit.CM) < 2) {
                motorStrafe.setPower(1);
            } else {
                motorStrafe.setPower(0);
            }

            telemetry.addData("Mode", "running");
            telemetry.addData("Front (cm)", front.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}
