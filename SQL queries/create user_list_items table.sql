USE [AndroidProj]
GO

/****** Object:  Table [dbo].[user_list_items]    Script Date: 10/06/2022 20:43:09 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user_list_items](
	[id] [int] NOT NULL,
	[list_id] [int] NOT NULL,
	[item_id] [int] NOT NULL,
	[amount] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[user_list_items]  WITH CHECK ADD  CONSTRAINT [FK__user_list__item___66603565] FOREIGN KEY([item_id])
REFERENCES [dbo].[items] ([itemID])
GO

ALTER TABLE [dbo].[user_list_items] CHECK CONSTRAINT [FK__user_list__item___66603565]
GO

ALTER TABLE [dbo].[user_list_items]  WITH CHECK ADD  CONSTRAINT [FK__user_list__list___656C112C] FOREIGN KEY([list_id])
REFERENCES [dbo].[user_lists] ([list_id])
GO

ALTER TABLE [dbo].[user_list_items] CHECK CONSTRAINT [FK__user_list__list___656C112C]
GO


