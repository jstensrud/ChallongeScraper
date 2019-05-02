USE [SmashTournyDB]
GO

/****** Object:  Table [dbo].[Match]    Script Date: 4/4/2019 9:29:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Match](
	[MatchID] [int] NOT NULL,
	[TournamentID] [int] NOT NULL,
	[WinnerTag] [varchar](30) NOT NULL,
	[LoserTag] [varchar](30) NOT NULL,
	[Score] [varchar](11) NOT NULL,
 CONSTRAINT [PK_Match] PRIMARY KEY CLUSTERED 
(
	[MatchID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Match]  WITH CHECK ADD  CONSTRAINT [PlayerTagToLoserTag] FOREIGN KEY([LoserTag])
REFERENCES [dbo].[Player] ([Tag])
GO

ALTER TABLE [dbo].[Match] CHECK CONSTRAINT [PlayerTagToLoserTag]
GO

ALTER TABLE [dbo].[Match]  WITH CHECK ADD  CONSTRAINT [PlayerTagToWinnerTag] FOREIGN KEY([WinnerTag])
REFERENCES [dbo].[Player] ([Tag])
GO

ALTER TABLE [dbo].[Match] CHECK CONSTRAINT [PlayerTagToWinnerTag]
GO

ALTER TABLE [dbo].[Match]  WITH CHECK ADD  CONSTRAINT [TournamentIDMatching] FOREIGN KEY([TournamentID])
REFERENCES [dbo].[Tournament] ([TournamentID])
GO

ALTER TABLE [dbo].[Match] CHECK CONSTRAINT [TournamentIDMatching]
GO

