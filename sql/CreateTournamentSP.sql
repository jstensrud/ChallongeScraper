USE [_SmashDB]
GO
--
-- Create empty tournament (if it doesn't exist)
--
-------------------------------------------
-- Demo:
-- EXEC [create_tournament] 0, 'smash', 'rose', '4/25/19', 'april smash'
--
-------------------------------------------
--
-- Revision History
--
-- 4/25/19 - create proc
--

CREATE PROCEDURE [create_tournament]
	(@TournamentID_1 [int],
	@Game_2 [varchar],
	@Organization_3 [varchar],
	@Date_4 [date],
	@Name_5 [varchar]
	)
AS
-- Validate parameters (add null checks)
IF EXISTS(SELECT * FROM [Tournament] WHERE [TournamentID] = @TournamentID_1)
	RETURN 1
IF NOT EXISTS(SELECT * FROM [Game] WHERE [Name] = @Game_2)
	RETURN 2
-- Insert tournament record
INSERT INTO [Tournament]
VALUES (@TournamentID_1, @Game_2, @Organization_3, @Date_4, @Name_5)
GO
