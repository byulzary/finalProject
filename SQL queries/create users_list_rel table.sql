USE [AndroidProj]
GO

/****** Object:  Table [dbo].[user_lists_rel]    Script Date: 10/06/2022 20:43:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user_lists_rel](
	[id] [int] NOT NULL,
	[user_id] [int] NOT NULL,
	[list_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[user_lists_rel]  WITH CHECK ADD FOREIGN KEY([list_id])
REFERENCES [dbo].[user_lists] ([list_id])
GO

ALTER TABLE [dbo].[user_lists_rel]  WITH CHECK ADD  CONSTRAINT [FK__user_list__user___5CD6CB2B] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([userID])
GO

ALTER TABLE [dbo].[user_lists_rel] CHECK CONSTRAINT [FK__user_list__user___5CD6CB2B]
GO


