USE [_SmashDB]
GO
--
-- Populate tournament rankings
--
-------------------------------------------
-- Demo:
-- EXEC [insert_tournament_placing] 0, 'smash', 'rose', '4/25/19', 'april smash'
--
-------------------------------------------
--
-- Revision History
--
-- 4/25/19 - create proc
--

CREATE PROCEDURE [insert_tournament_placing]
	(@PlayerTag_1 [varchar],
	@PlayerCrew_2 [varchar],
	@TournamentID_3 [int],
	@Seed_4 [int],
	@Placing_5 [int]
	)
AS
-- Validate parameters (add null checks)
IF NOT EXISTS(SELECT * FROM [Tournament] WHERE [TournamentID] = @TournamentID_3)
	RETURN 1
-- Create player if they don't exist
IF NOT EXISTS(SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_1)
	INSERT INTO [Player]
	VALUES (@PlayerTag_1, @PlayerCrew_2)
-- Insert tournament record
INSERT INTO [Participates_In]
VALUES (@PlayerTag_1, @TournamentID_3, @Seed_4, @Placing_5)
GO
