USE [_SmashDB]
GO
/****** Object:  StoredProcedure [dbo].[get_playerTourney_results]    Script Date: 5/10/2019 10:43:20 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[get_tourney_results]
	(@TournamentID_1	int
	)

AS

-- Make sure player and tourney are valid
IF @TournamentID_1 is NULL
	Return 1
IF NOT EXISTS(SELECT * FROM [Participates_In] WHERE [TournamentID] = @TournamentID_1)
	Return 1

-- Display

SELECT par.Placing, par.PlayerTag, par.Seed, t.[Name]
	FROM [Participates_In] par
	JOIN Tournament t ON t.TournamentID = par.TournamentID
	where par.TournamentID = @TournamentID_1
	ORDER BY par.Placing ASC
