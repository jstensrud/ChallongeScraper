USE [_SmashDB]
GO
--
-- Insert match details
--
-------------------------------------------
-- Demo:
-- EXEC [insert_match_details] 0, 0, 'a', 'b', 'oof'
--
-------------------------------------------
--
-- Revision History
--
-- 4/25/19 - create proc
--

CREATE PROCEDURE [insert_match_details]
	(@MatchID_1 [int],
	@TournamentID_2 [int],
	@WinnerTag_3 [varchar],
	@LoserTag_4 [varchar],
	@Score_5 [varchar]
	)
AS
-- Validate parameters (add null checks)
IF EXISTS(SELECT * FROM [Match] WHERE [MatchID] = @MatchID_1)
	RETURN 1
IF NOT EXISTS(SELECT * FROM [Tournament] WHERE [TournamentID] = @TournamentID_2)
	RETURN 2
-- Create player(s) if they don't exist
IF NOT EXISTS(SELECT * FROM [Player] WHERE [Tag] = @WinnerTag_3)
	INSERT INTO [Player]
	VALUES (@WinnerTag_3, null)
IF NOT EXISTS(SELECT * FROM [Player] WHERE [Tag] = @LoserTag_4)
	INSERT INTO [Player]
	VALUES (@LoserTag_4, null)
-- Insert match record
INSERT INTO [Match]
VALUES (@MatchID_1, @TournamentID_2, @WinnerTag_3, @LoserTag_4, @Score_5)
GO
