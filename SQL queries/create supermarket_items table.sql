USE [AndroidProj]
GO

/****** Object:  Table [dbo].[supermarket_items]    Script Date: 10/06/2022 20:43:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[supermarket_items](
	[id] [int] NOT NULL,
	[super_id] [int] NOT NULL,
	[item_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[supermarket_items]  WITH CHECK ADD FOREIGN KEY([item_id])
REFERENCES [dbo].[items] ([itemID])
GO

ALTER TABLE [dbo].[supermarket_items]  WITH CHECK ADD FOREIGN KEY([super_id])
REFERENCES [dbo].[supermarket] ([id])
GO


