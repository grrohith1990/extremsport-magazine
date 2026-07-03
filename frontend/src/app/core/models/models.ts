/**
 * Article model - mirrors backend domain model
 */
export interface Article {
  id: string;
  title: string;
  subtitle?: string;
  content: string;
  summary: string;
  authorId: string;
  authorName: string;
  status: ArticleStatus;
  accessType: AccessType;
  tags: string[];
  category: string;
  coverImageUrl?: string;
  createdAt: string;
  updatedAt: string;
  publishedAt?: string;
  archived: boolean;
}

export enum ArticleStatus {
  DRAFT = 'DRAFT',
  IN_REVIEW = 'IN_REVIEW',
  PUBLISHED = 'PUBLISHED',
  ARCHIVED = 'ARCHIVED'
}

export enum AccessType {
  PUBLIC = 'PUBLIC',
  PREMIUM = 'PREMIUM',
  EXCLUSIVE = 'EXCLUSIVE'
}

export interface User {
  id: string;
  username: string;
  email: string;
  displayName: string;
  roles: UserRole[];
  subscriptionActive: boolean;
}

export enum UserRole {
  READER = 'READER',
  SUBSCRIBER = 'SUBSCRIBER',
  AUTHOR = 'AUTHOR',
  EDITOR = 'EDITOR',
  MODERATOR = 'MODERATOR',
  ADMIN = 'ADMIN'
}

export interface ForumThread {
  id: string;
  title: string;
  authorId: string;
  authorName: string;
  category: string;
  postCount: number;
  lastPostAt: string;
  createdAt: string;
  pinned: boolean;
  locked: boolean;
}

export interface ForumPost {
  id: string;
  threadId: string;
  authorId: string;
  authorName: string;
  content: string;
  createdAt: string;
  updatedAt?: string;
  moderated: boolean;
}

export interface Subscription {
  id: string;
  userId: string;
  plan: SubscriptionPlan;
  status: SubscriptionStatus;
  startDate: string;
  endDate: string;
  autoRenew: boolean;
}

export enum SubscriptionPlan {
  MONTHLY = 'MONTHLY',
  YEARLY = 'YEARLY',
  PREMIUM = 'PREMIUM'
}

export enum SubscriptionStatus {
  ACTIVE = 'ACTIVE',
  CANCELLED = 'CANCELLED',
  EXPIRED = 'EXPIRED',
  TRIAL = 'TRIAL'
}

