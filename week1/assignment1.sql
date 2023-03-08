-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gradebook
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gradebook
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gradebook` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `gradebook` ;

-- -----------------------------------------------------
-- Table `gradebook`.`Instructor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Instructor` (
  `InstructorID` INT NOT NULL,
  `InstructorName` VARCHAR(45) NULL,
  PRIMARY KEY (`InstructorID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gradebook`.`Course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Course` (
  `CourseID` INT NOT NULL,
  `CourseName` VARCHAR(45) NULL,
  `Instructor_InstructorID` INT NOT NULL,
  PRIMARY KEY (`CourseID`),
  INDEX `fk_Course_Instructor1_idx` (`Instructor_InstructorID` ASC) VISIBLE,
  CONSTRAINT `fk_Course_Instructor1`
    FOREIGN KEY (`Instructor_InstructorID`)
    REFERENCES `gradebook`.`Instructor` (`InstructorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gradebook`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Student` (
  `StudentID` INT NOT NULL,
  `StudentName` VARCHAR(45) NULL,
  PRIMARY KEY (`StudentID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gradebook`.`Enrollment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Enrollment` (
  `EnrollmentID` INT NOT NULL,
  `Grade` VARCHAR(45) NULL,
  `Course_CourseID` INT NOT NULL,
  `Student_StudentID` INT NOT NULL,
  PRIMARY KEY (`EnrollmentID`),
  INDEX `fk_Enrollment_Course_idx` (`Course_CourseID` ASC) VISIBLE,
  INDEX `fk_Enrollment_Student1_idx` (`Student_StudentID` ASC) VISIBLE,
  CONSTRAINT `fk_Enrollment_Course`
    FOREIGN KEY (`Course_CourseID`)
    REFERENCES `gradebook`.`Course` (`CourseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Enrollment_Student1`
    FOREIGN KEY (`Student_StudentID`)
    REFERENCES `gradebook`.`Student` (`StudentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gradebook`.`Assignment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Assignment` (
  `AssignmentID` INT NOT NULL,
  `AssignmentName` VARCHAR(45) NULL,
  `Assignmentcol` DATETIME NULL,
  `Course_CourseID` INT NOT NULL,
  `Student_StudentID` INT NOT NULL,
  PRIMARY KEY (`AssignmentID`),
  INDEX `fk_Assignment_Course1_idx` (`Course_CourseID` ASC) VISIBLE,
  INDEX `fk_Assignment_Student1_idx` (`Student_StudentID` ASC) VISIBLE,
  CONSTRAINT `fk_Assignment_Course1`
    FOREIGN KEY (`Course_CourseID`)
    REFERENCES `gradebook`.`Course` (`CourseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Assignment_Student1`
    FOREIGN KEY (`Student_StudentID`)
    REFERENCES `gradebook`.`Student` (`StudentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gradebook`.`Grade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gradebook`.`Grade` (
  `GradeID` INT NOT NULL,
  `GradePercent` VARCHAR(45) NULL,
  `Assignment_AssignmentID` INT NOT NULL,
  `Student_StudentID` INT NOT NULL,
  PRIMARY KEY (`GradeID`),
  INDEX `fk_Grade_Assignment1_idx` (`Assignment_AssignmentID` ASC) VISIBLE,
  INDEX `fk_Grade_Student1_idx` (`Student_StudentID` ASC) VISIBLE,
  CONSTRAINT `fk_Grade_Assignment1`
    FOREIGN KEY (`Assignment_AssignmentID`)
    REFERENCES `gradebook`.`Assignment` (`AssignmentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grade_Student1`
    FOREIGN KEY (`Student_StudentID`)
    REFERENCES `gradebook`.`Student` (`StudentID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
