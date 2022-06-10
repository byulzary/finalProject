USE [AndroidProj]
GO

/****** Object:  Table [dbo].[users]    Script Date: 10/06/2022 20:43:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[users](
	[userID] [int] NOT NULL,
	[name] [varchar](1000) NOT NULL,
	[phone] [varchar](1000) NULL,
	[address] [varchar](1000) NULL,
	[email] [varchar](1000) NOT NULL,
	[password] [varchar](1000) NOT NULL,
 CONSTRAINT [PK__users__CB9A1CDFD8B92FAA] PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

