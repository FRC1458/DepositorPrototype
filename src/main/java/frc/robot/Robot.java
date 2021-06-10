// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX leftMotor = new WPI_TalonSRX(RobotConstants.leftMotorID);
  private final WPI_TalonSRX rightMotor = new WPI_TalonSRX(RobotConstants.rightMotorID);
  private final WPI_TalonSRX leftMotor2 = new WPI_TalonSRX(RobotConstants.leftMotor2ID);
  private final WPI_TalonSRX rightMotor2 = new WPI_TalonSRX(RobotConstants.rightMotor2ID);
  private final DifferentialDrive robotDrive = new DifferentialDrive(leftMotor, rightMotor);
  //private final Servo servo = new Servo(0);
  //private final Solenoid solenoid = new Solenoid(0);
  private final WPI_TalonSRX quantumIntake = new WPI_TalonSRX(RobotConstants.quantumIntakeID);

  private final Joystick stick = new Joystick(0);
  private final XboxController controller = new XboxController(0);

  public void robotInit() {
    leftMotor.setNeutralMode(NeutralMode.Brake);
    rightMotor.setNeutralMode(NeutralMode.Brake);
    leftMotor2.setNeutralMode(NeutralMode.Brake);
    rightMotor2.setNeutralMode(NeutralMode.Brake);

    leftMotor2.follow(leftMotor);
    rightMotor2.follow(rightMotor);
  }

  public void teleopInit() {
    //servo.setAngle(0);
    //solenoid.set(false);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    robotDrive.arcadeDrive(stick.getY(), -stick.getX());
    if(controller.getAButton()){
      quantumIntake.set(.5);
    }
    else if(controller.getBButton()){
      quantumIntake.set(-.5);
    }
  }
}