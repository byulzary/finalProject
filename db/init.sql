CREATE DATABASE AndroidProj
GO

USE [AndroidProj]
GO

/****** Object:  Table [dbo].[supermarket]    Script Date: 10/06/2022 20:42:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[supermarket](
	[id] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[map] [varchar](100) NULL,
PRIMARY KEY CLUSTERED
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

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


