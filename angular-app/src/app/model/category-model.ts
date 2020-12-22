export interface CategoryModel {
    id: number;
    name: string;
    types: Array<TypeModel>;
}
export interface TypeModel {
    id: number;
    name: string;
}
