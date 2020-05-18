import React, { Component } from 'react'
import './App.css'

import CalculateCircleArea from './containers/CalculateCircleArea'

class App extends Component {
  render () {
    return (
      <div className='col-md-6 col-md-offset-3 align-content-center'>
        <CalculateCircleArea />
      </div>
    )
  }
}

export default App
