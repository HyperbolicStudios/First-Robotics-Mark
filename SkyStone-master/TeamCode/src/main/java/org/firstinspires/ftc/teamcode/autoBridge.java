// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.concurrent.TimeUnit;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Auto UnderBridge", group="Exercises")
//@Disabled
public class autoBridge extends LinearOpMode {
    private DcMotor motorLeft; //Left Wheel
    private DcMotor motorRight;//Right Wheel
    private DcMotor motorStrafe;//Strafe Wheel
    private Servo leftFound; //Foundation Servo

    private DistanceSensor distanceLeft;//Left Distance Sensor
    private DistanceSensor distanceFront;//Front Distance Sensor
    private TouchSensor touchRight; //Right Touch Sensor

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.get(DcMotor.class, "left");     //Maps Hardware to Variables
        motorRight = hardwareMap.get(DcMotor.class, "right");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        leftFound = hardwareMap.get(Servo.class, "found1");

        distanceLeft = hardwareMap.get(DistanceSensor.class, "distanceLeft"); //Maps Sensors to Variables
        distanceFront = hardwareMap.get(DistanceSensor.class, "distanceFront");
        touchRight = hardwareMap.get(TouchSensor.class, "touchRight");

        //Initializes Telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)


        telemetry.addData("Mode", "waiting");
        telemetry.addData("Left (cm)", distanceLeft.getDistance(DistanceUnit.CM));
        telemetry.addData("Front (cm)", distanceFront.getDistance(DistanceUnit.CM));
        telemetry.update();

        // wait for start button.
        waitForStart();
        motorLeft.setPower(1);//starts driving
        motorRight.setPower(-1);
        TimeUnit.MILLISECONDS.sleep(2000);//keeps driving for 2 seconds
        motorRight.setPower(0);//stops driving, hopefully under bridge
        motorLeft.setPower(0);


    }
}
