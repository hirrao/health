import client from './client'

const test = () => client.get('test')

export const TestRepository = {
  test,
}
