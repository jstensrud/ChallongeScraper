USE [SmashTournyDB]
GO

/****** Object:  Table [dbo].[Tournament]    Script Date: 4/4/2019 9:19:02 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Tournament](
	[TournamentID] [int] NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Organization] [varchar](50) NULL,
	[Game] [varchar](50) NOT NULL FOREIGN KEY REFERENCES [Game]([Name]),
	[Date] [date] NOT NULL,
 CONSTRAINT [PK_Tournament] PRIMARY KEY CLUSTERED 
(
	[TournamentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


