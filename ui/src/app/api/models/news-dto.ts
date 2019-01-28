/* tslint:disable */
import { ArticleDto } from './article-dto';
export interface NewsDto {
  articles?: Array<ArticleDto>;
  category?: string;
  country?: string;
}
