USE [AndroidProj]
GO

/****** Object:  Table [dbo].[user_lists]    Script Date: 10/06/2022 20:43:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[user_lists](
	[list_id] [int] NOT NULL,
	[name] [varchar](50) NULL,
	[creation_time] [timestamp] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[list_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


