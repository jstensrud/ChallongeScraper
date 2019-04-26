USE [_SmashDB]
GO
--
-- Insert player details
--
-------------------------------------------
-- Demo:
-- EXEC [insert_player_details] 0, 0, 'a', 'b', 'oof'
--
-------------------------------------------
--
-- Revision History
--
-- 4/25/19 - create proc
--

CREATE PROCEDURE [insert_player_details]
	(@PlayerTag_1 [varchar],
	@PlayerCrew_2 [varchar] = NULL,
	@CharName_3 [varchar],
	@CharGame_4 [varchar],
	@CharSkin_5 [varchar]
	)
AS
-- Validate parameters (add null checks)
IF EXISTS(SELECT * FROM [Player] WHERE [Tag] = @PlayerTag_1)
	RETURN 1
-- Insert player record
INSERT INTO [Player]
VALUES (@PlayerTag_1, @PlayerCrew_2)
IF EXISTS(SELECT * FROM [Character] WHERE [Name] = @CharName_3 AND [Game] = @CharGame_4 AND [Skin] = @CharSkin_5)
	INSERT INTO [Mains]
	VALUES (@PlayerTag_1, @CharName_3, @CharGame_4, @CharSkin_5)
GO
