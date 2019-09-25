import React, { PureComponent } from 'react';
import { Affix, Button, Form, Icon, Input, message, Tabs } from "antd";
import { connect } from "dva";
import { InResp } from "@/pages/user/in/data";
import { formatMessage, FormattedMessage } from 'umi-plugin-react/locale'

@connect(({ model, loading }) => ({ model, loading }))
class In extends PureComponent<any> {
    state = {
        amount: '',
        payType: 'alipay',
        comment: '',
        alipayActiveKey: 'alipay',
        amountInvalid: false,
    }

    callback = (payway: string) => {
        if ('alipay' == payway) {
            this.setState({
                comment: '',
                payType: this.state.alipayActiveKey
            })
        } else {
            this.setState({
                comment: '',
                payType: payway
            })
        }
    }

    // 确认下单
    ok = () => {
        console.log(this.state)
        const { payType, amount, comment } = this.state

        // 参数检查, 手动容易理解, todo, 改成Form表单验证器
        if ('' == amount) {
            message.warning(formatMessage({ id: '请输入金额' }))
            this.setState({
                amountInvalid: true
            })
            return
        }
        console.log(amount)
        console.log(typeof amount)
        if (0.01 > Number.parseFloat(amount) || 50000 < Number.parseFloat(amount)) {
            message.warning(formatMessage({ id: '下单金额范围为0.01至50000之间' }))
            this.setState({
                amountInvalid: true
            })
            return
        }
        this.setState({
            amountInvalid: false
        })
        // 待完善
        if ('' == comment) {
            message.error(formatMessage({ id: '请填写正确的备注信息, 否则无法到账!!!' }))
            return
        }
        this.props.dispatch({
            type: 'model/createIn',
            payload: { payType, amount, comment },
            // 直接在这里处理了..
            callback: (response: { code: number, msg: string, data: InResp }) => {
                if (response && 200 == response.code) {
                    message.success(formatMessage({ id: '下单成功' }))
                    const data = response.data
                    // todo, 当前项目里自定义最终页面
                    // router.push(`/user/in/query/apiKey/${data.apiKey}/tradeNo/${data.tradeNo}/payType/${payType}/amount/${amount}/comment/${comment}`)

                    // 使用simple-diamond的最终页面
                    window.location.href = '/api/in/redirect?apiKey=' + data.apiKey + '&tradeNo=' + data.tradeNo
                } else {
                    message.error(response.msg || formatMessage({ id: '下单失败, 请稍候重试' }))
                }
            }
        });
    }

    render() {
        const { loading } = this.props
        return (
            <div style={{ textAlign: 'center' }}>
                <hr/>

                <Tabs defaultActiveKey="alipay" style={{ minHeight: '180px' }} onChange={this.callback}>
                    <Tabs.TabPane tab={<div><Icon type="alipay-circle"/><FormattedMessage id="支付宝"/></div>}
                                  key="alipay">
                        <Tabs defaultActiveKey="alipay" size="small" type="card" onTabClick={(value: string) => {
                            this.setState({
                                comment: '',
                                alipayActiveKey: value,
                                payType: value,
                            })
                        }} activeKey={this.state.alipayActiveKey}>
                            <Tabs.TabPane tab={<div><FormattedMessage id="转到支付宝账户"/></div>} key="alipay">
                                <Input.Group>
                                    <label><FormattedMessage id="请填写您转账的支付宝账号"/></label>
                                    <Input size="large" placeholder={formatMessage({ id: '请填写您转账的支付宝账号' })}
                                           value={this.state.comment}
                                           onChange={(e) => {
                                               this.setState({
                                                   comment: e.target.value
                                               })
                                           }}/>
                                </Input.Group>
                            </Tabs.TabPane>
                            <Tabs.TabPane tab={<div><FormattedMessage id="支付宝转银行卡"/></div>} key="bank">
                                <Input.Group>
                                    <label><FormattedMessage id="请填写您的付款账户姓名"/></label>
                                    <Input size="large" placeholder={formatMessage({ id: '请填写您的付款账户姓名' })}
                                           value={this.state.comment}
                                           onChange={(e) => {
                                               this.setState({
                                                   comment: e.target.value
                                               })
                                           }}/>
                                </Input.Group>
                            </Tabs.TabPane>
                        </Tabs>

                    </Tabs.TabPane>
                    <Tabs.TabPane tab={<div><Icon type="credit-card"/><FormattedMessage id="银行卡"/></div>} key="bank">
                        <br/>
                        <Input.Group>
                            <label><FormattedMessage id="请填写您的银行卡付款账户姓名"/></label>
                            <Input size="large" placeholder={formatMessage({ id: '请填写您的银行卡付款账户姓名' })}
                                   value={this.state.comment}
                                   onChange={(e) => {
                                       this.setState({
                                           comment: e.target.value
                                       })
                                   }}/>
                        </Input.Group>
                    </Tabs.TabPane>
                    <Tabs.TabPane tab={<div><Icon type="wechat"/><FormattedMessage id="微信"/></div>} key="wepay">
                        <br/>
                        <Input.Group>
                            <label><FormattedMessage id="请填写您转账的微信账号"/></label>
                            <Input size="large" placeholder={formatMessage({ id: '请填写您转账的微信账号' })}
                                   value={this.state.comment}
                                   onChange={(e) => {
                                       this.setState({
                                           comment: e.target.value
                                       })
                                   }}/>
                        </Input.Group>
                    </Tabs.TabPane>
                </Tabs>
                <br/>
                <Form.Item
                    validateStatus={this.state.amountInvalid ? "error" : "success"}
                    help={this.state.amountInvalid && formatMessage({ id: '0.01至50000之间的数字' })}
                >
                    <Input addonBefore={formatMessage({ id: '金额' })}
                           addonAfter={formatMessage({ id: '元' })}
                           size="large" style={{ maxWidth: "250px" }} type="number"
                           onChange={e => {
                               this.setState({
                                   amount: e.target.value
                               })
                           }} value={this.state.amount}/>
                </Form.Item>
                <br/>
                <br/>
                <br/>
                <Affix offsetBottom={50}>
                    <Button type="primary" style={{ maxWidth: "50%", minWidth: '250px' }}
                            loading={loading.effects['model/createIn']}
                            onClick={this.ok}><FormattedMessage id="确认下单"/></Button>
                </Affix>
            </div>
        );
    }
}

export default In;
