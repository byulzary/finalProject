USE [AndroidProj]
GO

/****** Object:  Table [dbo].[items]    Script Date: 10/06/2022 20:42:50 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[items](
	[itemID] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[itemDesc] [varchar](100) NULL,
	[loc_x] [float] NOT NULL,
	[loc_y] [float] NOT NULL,
	[loc_z] [float] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[itemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


