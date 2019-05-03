USE [_SmashDB]
GO

--
-- Get tournament ID from name (and maybe organizer name)
--
-------------------------------------------
-- Demo:
-- EXEC [get_tournament_id] 'smashsmash'
--
-------------------------------------------
--
-- Revision History
--
-- 5/2/19 - create proc
--

CREATE PROCEDURE [get_tournament_id]
	(@TournamentName_1 [varchar](50))
AS
IF @TournamentName_1 IS NULL OR NOT EXISTS(SELECT * FROM [Tournament] WHERE [Name] = @TournamentName_1)
	RAISERROR ('Tournament name null or non-existent', 5, 0)
SELECT TOP 1 TournamentID FROM [Tournament] WHERE [Name] = @TournamentName_1
