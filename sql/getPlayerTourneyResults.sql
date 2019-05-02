USE [_SmashDB]
GO

CREATE PROCEDURE [dbo].[get_playerTourney_results]
	(@TournamentID_1 [int],
	@PlayerTag_2	varchar(30)
	)

AS

-- Make sure player and tourney are valid
IF @TournamentID_1 is NULL OR @PlayerTag_2 is NULL
	Return 1
IF NOT EXISTS (SELECT * FROM [Tournament] WHERE [TournamentID] = @TournamentID_1)
	RETURN 1
IF NOT EXISTS(SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_2)
	Return 1

-- Display

SELECT t.[Name], p.Tag, par.Seed, par.Placing
	FROM [Participates_In] par
	JOIN Player p ON p.Tag = @PlayerTag_2
	JOIN Tournament t ON t.TournamentID = @TournamentID_1
	
