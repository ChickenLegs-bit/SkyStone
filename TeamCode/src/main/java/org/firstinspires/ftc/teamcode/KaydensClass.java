package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.android.AndroidAccelerometer;
import org.firstinspires.ftc.robotcore.external.android.AndroidGyroscope;
import org.firstinspires.ftc.robotcore.external.android.AndroidOrientation;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class KaydensClass extends LinearOpMode {
    private AndroidGyroscope gyroSensor;
    private AndroidOrientation orientSensor;
    private AndroidAccelerometer accelSensor;
    private DcMotor motorTest;
    private Servo servoTest;

    @Override
    public void runOpMode() {
       gyroSensor = hardwareMap.get(AndroidGyroscope.class, "gyroSensor") ;
       orientSensor = hardwareMap.get(AndroidOrientation.class,"orientSensor");
       accelSensor = hardwareMap.get(AndroidAccelerometer.class,"accelSensor");

       motorTest = hardwareMap.get(DcMotor.class,"motorTest");
       servoTest = hardwareMap.get(Servo.class, "servoTest");

       telemetry.addData("Status", "Initiated Gryro " + gyroSensor);
       telemetry.update();

        double tgtPower = 0;

        while (opModeIsActive()) {
           tgtPower = -this.gamepad1.left_stick_y;
           motorTest.setPower(tgtPower);
           // check to see if we need to move the servo.
           if (gamepad1.y) {
               // move to 0 degrees.
               servoTest.setPosition(0);
           } else if (gamepad1.x || gamepad1.b) {
               // move to 90 degrees.
               servoTest.setPosition(0.5);
           } else if (gamepad1.a) {
               // move to 180 degrees.
               servoTest.setPosition(1);
           }
           telemetry.addData("Servo Position", servoTest.getPosition());

           AngleUnit unit = gyroSensor.getAngleUnit();
           float gyroX = unit.toDegrees(this.gyroSensor.getX());
           float gyroY = unit.toDegrees(this.gyroSensor.getY());
           float gyroZ = unit.toDegrees(this.gyroSensor.getZ());

           double tgtPower1 = this.gamepad1.left_stick_y;
           double tgtPower2 = this.gamepad1.left_stick_x;
           motorTest.setPower(tgtPower1);
           telemetry.addData("Motor Power","Motor set to " + tgtPower1);
           telemetry.addData("Status","Running");

           telemetry.update();
       }
    }

}
