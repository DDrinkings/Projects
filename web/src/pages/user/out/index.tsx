import React from 'react';
import 'antd/dist/antd.css';
import { Button, DatePicker, Form, Input, Select, TimePicker, message } from 'antd';
import { Link, router } from "umi";
import { login } from "@/pages/user/out/service";
import { response } from "express";


// @Form.create()
const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 5 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 12 },
  },
};

class Index extends React.Component {
  click = () => {
    console.log(345)
  }

  ok = () => {
    const { validateFields } = this.props.form;

    validateFields((errors, values) => {
      console.log(errors)
      //console.log(values)
      if (null === errors) {
        login(values).then(response => {
          console.log(response)
          if (response && 200 === response.code) {
            message.success('登录成功')
          } else {
            message.error(response.message)
          }
        })
      }
    })
  }
  commit = () => {
  }

  render() {
    const { getFieldDecorator } = this.props.form;

    return (
      <Form {...formItemLayout}>


        <Form.Item
          label="Validating"
          hasFeedback
          validateStatus="validating"
          help="The information is being validated..."
        >
          <Input placeholder="I'm the content is being validated" id="validating"/>
        </Form.Item>

        <Form.Item label="Success" hasFeedback validateStatus="success">
          <Input placeholder="I'm the content" id="success"/>
        </Form.Item>

        <Form.Item label="Warning" hasFeedback validateStatus="warning">
          <Input placeholder="Warning" id="warning2"/>
        </Form.Item>

        <Form.Item
          label="Fail"
          hasFeedback
          validateStatus="error"
          help="Should be combination of numbers & alphabets"
        >
          <Input placeholder="unavailable choice" id="error2"/>
        </Form.Item>

        <Form.Item label="Success" hasFeedback validateStatus="success">
          <DatePicker style={{ width: '100%' }}/>
        </Form.Item>

        <Form.Item label="Warning" hasFeedback validateStatus="warning">
          <TimePicker style={{ width: '100%' }}/>
        </Form.Item>


        <hr/>

        事件
        <Button onClick={() => {
          console.log(123)
        }}>按钮 </Button>

        <hr/>

        路由跳转
        <Button><Link to="/user/in">跳转到user in页面</Link></Button>
        <Button onClick={() => {
          router.push('/user/in')
        }}>跳转到user in页面</Button>

        <hr/>
        <Form.Item>
          {
            getFieldDecorator('username', {
              rules: [
                {
                  required: true,
                  message: '不能为空',
                },
                {
                  min: 2,
                  message: '最少2位',
                },
                {
                  max: 8,
                  message: '最多8位',
                },
                {
                  pattern: /^[_0-9a-zA-Z]{2,8}$/,
                  message: '需要匹配正则',
                },
              ],
            })(<Input placeholder="请输入用户名" autoComplete="off"/>)
          }
        </Form.Item>

        <hr/>

        发请求
        <Button type="primary" onClick={this.ok}>提交</Button>

        <hr/>
        mock测试

        <hr/>
        练习
        <Form.Item>
          {
            getFieldDecorator('username', {
              rules: [
                {
                  required: true,
                  message: '不能为空',
                }
              ]
            })(<Input placeholder="请输入用户名"/>)
          }
        </Form.Item>
        <Button type="primary" onClick={() => {
          router.push('/user/in')
        }}>确定</Button>
        <Button type="primary" onClick={this.commit}>提交</Button>

      </Form>
    );
  }
}

// export default Index;
export default Form.create()(Index);
