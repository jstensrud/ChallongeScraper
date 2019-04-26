USE [_SmashDB]
GO
--
-- Create and populate Game table
--
-------------------------------------------
--
-- Revision History
--
-- 4/25/19 - create script
--

/****** Object:  Table [dbo].[Game]    Script Date: 4/25/2019 8:34:02 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Game](
	[Name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT INTO [dbo].[Game]
VALUES ('Super Smash Bros. for Wii U')
INSERT INTO [dbo].[Game]
VALUES ('Super Smash Bros. Ultimate')
INSERT INTO [dbo].[Game]
VALUES ('Super Smash Bros. Brawl')
INSERT INTO [dbo].[Game]
VALUES ('Super Smash Bros. Melee')
INSERT INTO [dbo].[Game]
VALUES ('Super Smash Bros.')
GO
