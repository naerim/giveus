import { TableInstance } from 'react-table'

declare module 'react-table' {
  export interface TableInstance<D extends Record<string, unknown>> {
    setGlobalFilter
    page
    nextPage
    previousPage
    canPreviousPage
    canNextPage
    pageOptions
  }

  export interface TableState<D extends Record<string, unknown>> {
    pageIndex
  }

  export interface HeaderGroup<D extends Record<string, unknown>> {
    getSortByToggleProps: (props?: any) => any
    isSorted: boolean
    isSortedDesc: boolean
  }
}

// react-table 모듈의 인터페이스를 확장하여 속성을 명시적으로 추가
