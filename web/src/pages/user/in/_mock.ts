import { Request, Response } from 'express';

export default {
  'POST /api/login': (req: Request, res: Response) => {
    console.log(req.body)

    if ('test' === req.body.username) {
      setTimeout(() => {
        res.send({
          code: 200,
          message: 'ok',
          data: null
        })
      }, 2000)
    } else {
      res.send({
        code: 401,
        message: '用户不存在',
        data: null
      })
    }
  }
}
