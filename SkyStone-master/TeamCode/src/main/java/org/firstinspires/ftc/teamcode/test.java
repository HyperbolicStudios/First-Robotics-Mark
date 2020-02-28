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

@Autonomous(name="Sensor Values", group="Exercises")
//@Disabled
public class test extends LinearOpMode {
    private DcMotor motorLeft; //Not relevant
    private DcMotor motorRight;
    private DcMotor armA1;
    private DcMotor motorStrafe;
    private Servo leftFound;
    private Servo rightFound;
    private Servo grab;
    private Servo found1;
    private Servo grabPivot;
    private DistanceSensor front;
    private DistanceSensor back;

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.get(DcMotor.class, "left");     //Create devices - don't worry about this
        motorRight = hardwareMap.get(DcMotor.class, "right");
        armA1 = hardwareMap.get(DcMotor.class, "ArmA1");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        leftFound = hardwareMap.get(Servo.class, "found1");
        grab = hardwareMap.get(Servo.class, "grab");

        grabPivot = hardwareMap.get(Servo.class, "grabPivot");

        front = hardwareMap.get(DistanceSensor.class, "distanceFront");
        back = hardwareMap.get(DistanceSensor.class, "distanceLeft");

        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)


        telemetry.addData("Mode", "waiting");
        telemetry.addData("Front (cm)", front.getDistance(DistanceUnit.CM));
        telemetry.addData("Back (cm)", back.getDistance(DistanceUnit.CM));
        telemetry.update();

        // wait for start button.

        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Mode", "waiting");
            telemetry.addData("Front (cm)", front.getDistance(DistanceUnit.CM));
            telemetry.addData("Back (cm)", back.getDistance(DistanceUnit.CM));
            telemetry.update();
        }
    }
}