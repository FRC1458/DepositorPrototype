// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private  WPI_TalonSRX leftMotor;
  private  WPI_TalonSRX rightMotor;
  private XboxController controller;

  private Joystick leftStick;

  private boolean button1;
  private boolean button2;

  public Robot() {
    super(0.03);
    leftMotor = new WPI_TalonSRX(RobotConstants.leftMotorID);
    rightMotor = new WPI_TalonSRX(RobotConstants.rightMotorID);
    leftStick = new Joystick(0);
    controller = new XboxController(0);
    button1 = leftStick.getRawButton(1);
    button2 = leftStick.getRawButton(2);
  }

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {
    button1 = leftStick.getRawButton(1);
    button2 = leftStick.getRawButton(2);
    
    if (controller.getAButton() || button1) {
      leftMotor.set(1);
      rightMotor.set(-1);
    }
    else if (controller.getBButton() || button2) {
      leftMotor.set(-0.3);
      rightMotor.set(0.3);
    }
    else {
      leftMotor.set(0);
      rightMotor.set(0);
    }
  }
}