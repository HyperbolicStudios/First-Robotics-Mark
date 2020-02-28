// simple autonomous program that drives bot forward 2 seconds then ends.

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

// below is the Annotation that registers this OpMode with the FtcRobotController app.
// @Autonomous classifies the OpMode as autonomous, name is the OpMode title and the
// optional group places the OpMode into the Exercises group.
// uncomment the @Disable annotation to remove the OpMode from the OpMode list.

@Autonomous(name="Foundation Blue", group="Exercises")
//@Disabled
public class BlueFound extends LinearOpMode {
    private DcMotor motorLeft; //Left Wheel
    private DcMotor motorRight;//Right Wheel
    private DcMotor motorStrafe;//Strafe Wheel
    private Servo leftFound; //Foundation Servo
    private Servo rightFound;

    private DistanceSensor distanceLeft;//Left Distance Sensor
    private DistanceSensor distanceFront;//Front Distance Sensor
   // private TouchSensor touchRight; //Right Touch Sensor

    // called when init button is  pressed.

    @Override
    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.get(DcMotor.class, "left");     //Maps Hardware to Variables
        motorRight = hardwareMap.get(DcMotor.class, "right");
        motorStrafe = hardwareMap.get(DcMotor.class, "strafe");
        leftFound = hardwareMap.get(Servo.class, "found1");
        rightFound = hardwareMap.get(Servo.class, "found2");
        distanceLeft = hardwareMap.get(DistanceSensor.class, "distanceLeft"); //Maps Sensors to Variables
        distanceFront = hardwareMap.get(DistanceSensor.class, "distanceFront");
      //  touchRight = hardwareMap.get(TouchSensor.class, "touchRight");

        //Initializes Telemetry
        telemetry.addData("Status", "Initialized");
        telemetry.update();         // Wait for the game to start (driver presses PLAY)


        telemetry.addData("Mode", "waiting");
        telemetry.addData("Left (cm)", distanceLeft.getDistance(DistanceUnit.CM));
        telemetry.addData("Front (cm)", distanceFront.getDistance(DistanceUnit.CM));
        telemetry.update();

        // wait for start button.

        waitForStart();
        while (opModeIsActive()) {
            leftFound.setPosition(180); //Raises Servo
            rightFound.setPosition(180);
            motorLeft.setPower(-1); //Drive "Forward" - actually backwards
            motorRight.setPower(1);
            while (true) {

                if (distanceFront.getDistance(DistanceUnit.CM) < 20 && distanceFront.getDistance(DistanceUnit.CM) > 5) { //condition: wall is close

                    motorLeft.setPower(1);
                    motorRight.setPower(-1);//stop driving

                    TimeUnit.MILLISECONDS.sleep(200);
                    motorRight.setPower(0);
                    motorLeft.setPower(0);




                    motorStrafe.setPower(1);   //start strafing
                    break;
                } else {
                    motorStrafe.setPower(0);//Stop Strafing
                }

                //Update Telemetry
                telemetry.addData("Mode", "running");
                telemetry.addData("Left (cm)", distanceLeft.getDistance(DistanceUnit.CM));
                telemetry.addData("Front (cm)", distanceLeft.getDistance(DistanceUnit.CM));
                telemetry.update();
            }
            while (true) {
                if (distanceLeft.getDistance(DistanceUnit.CM) < 15 && distanceLeft.getDistance(DistanceUnit.CM) > 5) { //Condition: foundation

                    motorStrafe.setPower(0); //stops strafe
                    leftFound.setPosition(0); //drop servo
                    rightFound.setPosition(0);
                    TimeUnit.MILLISECONDS.sleep(2000);

                    motorStrafe.setPower(-1); //strafe backwards, pulling foundation
                   /* motorLeft.setPower(1);//Starts turning
                    motorRight.setPower(-1);
                    TimeUnit.MILLISECONDS.sleep(750); //wait one second

                    //motorRight.setPower(-1);//stops turning
                    //motorLeft.setPower(1); */
                    TimeUnit.MILLISECONDS.sleep(9000);
                    motorStrafe.setPower(0);
                    //UPDATE TELEMETRY
                    telemetry.addData("Mode", "running");
                    telemetry.addData("Left (cm)", distanceLeft.getDistance(DistanceUnit.CM));
                    telemetry.addData("Front (cm)", distanceFront.getDistance(DistanceUnit.CM));
                    telemetry.update();
                    break;
                }
            }


            break;
        }
    }
}
