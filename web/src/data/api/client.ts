import ky from 'ky'

const client = ky.create({
  prefixUrl: '/api',
  timeout: 10000,
})

export default client
