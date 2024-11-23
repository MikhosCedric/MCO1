CREATE TABLE
  `course_syllabus` (
    `submission_type_id` int NOT NULL AUTO_INCREMENT,
    `course_id` int NOT NULL,
    `submission_type_name` varchar(64) NOT NULL,
    `submission_percentage` float NOT NULL,
    PRIMARY KEY (`submission_type_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

  CREATE TABLE
  `courses` (
    `course_id` int NOT NULL AUTO_INCREMENT,
    `course_code` varchar(50) NOT NULL,
    `course_units` int DEFAULT NULL,
    PRIMARY KEY (`course_id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 14843 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `records` (
    `record_id` int NOT NULL AUTO_INCREMENT,
    `student_id` int NOT NULL,
    `section_id` int NOT NULL,
    `enrollment_date` date NOT NULL,
    PRIMARY KEY (`record_id`),
    KEY `fk_record_student` (`student_id`),
    KEY `fk_record_course` (`section_id`),
    CONSTRAINT `fk_record_course` FOREIGN KEY (`section_id`) REFERENCES `sections` (`class_id`),
    CONSTRAINT `fk_record_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
  ) ENGINE = InnoDB AUTO_INCREMENT = 2 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `section_details` (
    `class_id` int DEFAULT NULL,
    `section_code` varchar(10) DEFAULT NULL,
    `course_id` int DEFAULT NULL,
    `course_code` varchar(10) DEFAULT NULL,
    `section_teacher` varchar(100) DEFAULT NULL,
    `section_schedule` varchar(100) DEFAULT NULL,
    `section_venue` varchar(10) DEFAULT NULL,
    KEY `fk_class_id` (`class_id`),
    KEY `fk_course_id` (`course_id`),
    CONSTRAINT `fk_class_id` FOREIGN KEY (`class_id`) REFERENCES `sections` (`class_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `sections` (
    `class_id` int NOT NULL,
    `course_id` int NOT NULL,
    `teacher_id` int NOT NULL,
    `class_schedule` varchar(255) DEFAULT NULL,
    `section_capacity` int DEFAULT NULL,
    PRIMARY KEY (`class_id`),
    KEY `fk_section_course` (`course_id`),
    KEY `fk_section_teacher` (`teacher_id`),
    CONSTRAINT `fk_section_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `students` (
    `student_id` int NOT NULL,
    `student_name` varchar(100) NOT NULL,
    `contact_information` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`student_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `submissions` (
    `grade_id` int NOT NULL AUTO_INCREMENT,
    `student_id` int NOT NULL,
    `section_id` varchar(8) NOT NULL,
    `submission_type` int NOT NULL,
    `grade` float NOT NULL,
    PRIMARY KEY (`grade_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE
  `teachers` (
    `teacher_id` int NOT NULL,
    `name` varchar(100) NOT NULL,
    `department` varchar(50) NOT NULL,
    `teacher_email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`teacher_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
