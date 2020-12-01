CREATE TABLE MyUser (
    userID INT PRIMARY KEY IDENTITY (1,1),
    name VARCHAR (50) NOT NULL,
    sureName VARCHAR (50) NOT NULL,
    email VARCHAR (255) NOT NULL,
    login VARCHAR (50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    isAdmin TINYINT NOT NULL,
    createdAt DATETIME NOT NULL
);

CREATE TABLE Address (
    addressID INT PRIMARY KEY IDENTITY (1,1),
    city VARCHAR (200) NOT NULL,
    street VARCHAR (255) NOT NULL,
    streetNumber INT NOT NULL,
);

CREATE TABLE Pitch (
    pitchID INT PRIMARY KEY IDENTITY (1,1),
    addressID INT FOREIGN KEY REFERENCES Address(addressID),
    capacity INT NULL,
    name VARCHAR(150) NOT NULL,
);

CREATE TABLE Referee (
    refereeID INT PRIMARY KEY IDENTITY (1,1),
    name VARCHAR (50) NOT NULL,
    sureName VARCHAR (50) NOT NULL,
    email VARCHAR (255) NOT NULL,
    dateOfBirth DATETIME NOT NULL
);

CREATE TABLE Match (
    matchID INT PRIMARY KEY IDENTITY (1,1),
    postponed TINYINT NOT NULL,
    date DATETIME NOT NULL
    pitchID INT FOREIGN KEY REFERENCES Pitch(pitchID)
);

CREATE TABLE League (
    leagueID INT PRIMARY KEY IDENTITY (1,1),
    name VARCHAR(200) NOT NULL,
    category INT NOT NULL
);

CREATE TABLE Team (
    teamID INT PRIMARY KEY IDENTITY (1,1),
    leagueID INT FOREIGN KEY REFERENCES League(leagueID),
    name VARCHAR(200) NOT NULL,
    rank INT NOT NULL,
    covid TINYINT NOT NULL,
    quarantinedFrom DATETIME NULL
);

CREATE TABLE Coach (
    memberID INT PRIMARY KEY IDENTITY (1,1),
    teamID INT FOREIGN KEY REFERENCES Team(teamID),
    name VARCHAR(50) NOT NULL,
    sureName VARCHAR(50) NOT NULL,
    dateOfBirth DATETIME NOT NULL,
    covid TINYINT NOT NULL,
    quarantinedFrom DATETIME NULL,
    email VARCHAR(255) NOT NULL,
    license VARCHAR(4) NOT NULL
);

CREATE TABLE Player (
    memberID INT PRIMARY KEY IDENTITY (1,1),
    teamID INT FOREIGN KEY REFERENCES Team(teamID),
    name VARCHAR(50) NOT NULL,
    sureName VARCHAR(50) NOT NULL,
    dateOfBirth DATETIME NOT NULL,
    covid TINYINT NOT NULL,
    quarantinedFrom DATETIME NULL,
    email VARCHAR(255) NOT NULL,
    stick VARCHAR(4) NULL
);

CREATE TABLE Stats (
    statsID INT PRIMARY KEY IDENTITY (1,1),
    playerID INT FOREIGN KEY REFERENCES Player(memberID),
    assists INT NULL,
    goals INT NULL,
    points INT NULL
);

CREATE TABLE TeamMatch (
    teamMatchID INT PRIMARY KEY IDENTITY (1,1),
    matchID INT FOREIGN KEY REFERENCES Match(matchID),
    firstTeamID INT FOREIGN KEY REFERENCES Team(teamID),
    secondTeamID INT FOREIGN KEY REFERENCES Team(teamID),
    firstRefereeID INT FOREIGN KEY REFERENCES Referee(refereeID),
    secondRefereeID INT FOREIGN KEY REFERENCES Referee(refereeID),
    firstTeamGoals INT NOT NULL,
    secondTeamGoals INT NOT NULL,
);