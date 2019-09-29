import React from 'react';
import 'antd/dist/antd.css';
import { Button } from "antd";
import { connect } from "dva";

// @connect(state => {
//   console.log(state)
//   return state
// })
// @connect(state => state)
class Index extends React.Component {
  state = { username: '', phone: '18012312312', count: 0 }

  render() {
    console.log(this.props)
    return (
      <>hi
        <hr/>
        state
        <div>username: {this.state.username}</div>
        <div>phone: {this.state.phone}</div>
        <div>count: {this.state.count}</div>

        <Button onClick={() => {
          // 1
          // this.state.count = this.state.count + 1
          // 2
          // this.setState({
          //   count: this.state.count + 1
          // })
          // 3
          this.setState((state) => {
            console.log(state)
            return {
              ...state, count: state.count + 1
            }
          })
        }}>count ++</Button>

        <hr/>
        // 解构 es6
        {/*const {aa} = bb*/}
        <Button onClick={() => {
          const aa = { a: "123", b: "456" }
          console.log(aa);
          const bb = {
            ...aa, c: "ccc"
          }
          console.log(bb)
        }}>解构</Button>

        <hr/>
        <Button onClick={() => {
          this.props.dispatch({
            type: 'out2/login',
            payload: { username: "test", password: '123123' },
            callback: (response) => {

            }
          })
        }} loading={this.props.loading.effects['out2/login']}>dispatch</Button>

        <div>out2Data, username: {this.props.out2.out2Data.username}</div>
        <div>out2Data, count: {this.props.out2.out2Data.count}</div>

        <hr/>
      </>
    );
  }
}

// export default Index;
export default connect(state => {
  console.log(state);
  return state
})(Index);
