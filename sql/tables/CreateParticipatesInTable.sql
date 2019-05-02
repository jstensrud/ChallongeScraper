USE [SmashTournyDB]
GO

/****** Object:  Table [dbo].[Participates_In]    Script Date: 4/4/2019 9:44:30 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Participates_In](
	[PlayerTag] [varchar](30) NOT NULL,
	[TournamentID] [int] NOT NULL,
	[Seed] [int] NOT NULL,
	[Placing] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Participates_In]  WITH CHECK ADD  CONSTRAINT [FK_Participates_In_Player] FOREIGN KEY([PlayerTag])
REFERENCES [dbo].[Player] ([Tag])
GO

ALTER TABLE [dbo].[Participates_In] CHECK CONSTRAINT [FK_Participates_In_Player]
GO

ALTER TABLE [dbo].[Participates_In]  WITH CHECK ADD  CONSTRAINT [FK_Participates_In_Tournament] FOREIGN KEY([TournamentID])
REFERENCES [dbo].[Tournament] ([TournamentID])
GO

ALTER TABLE [dbo].[Participates_In] CHECK CONSTRAINT [FK_Participates_In_Tournament]
GO

