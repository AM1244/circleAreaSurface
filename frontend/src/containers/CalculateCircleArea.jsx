import React, { Component } from 'react'

/* Import Components */

import Button from '../components/Button'

class CalculateCircleArea extends Component {
  constructor (props) {
    super(props)
    this.state = {
      error: null,
      data: [],
      value: ''
    }
    this.onSubmit = this.handleSubmit.bind(this)
    this.handleChange = this.handleChange.bind(this)
  }

  handleChange (e) {
    this.setState({ value: e.target.value })
  }

  handleSubmit (e) {
    e.preventDefault()
    const input = { radius: this.state.value }
    fetch('http://localhost:8080/area', {
      method: 'POST',
      body: JSON.stringify(input),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(res => res.json())
      .then(response => {
        console.log(response)
        if (response.error) {
          console.log('Error')

          this.setState({ error: response.error })
        } else {
          console.log('Success:', response)
          this.setState({ data: response, error: null })
        }
      })
  }

  render () {
    const { error, data } = this.state
    return (
      <div>
        <label>
          <h2>Calculate Surface Area of Circle</h2>{' '}
        </label>
        <br />
        <label htmlFor='addCard'>
          <h3>Add the radius of the circle</h3>
        </label>
        <form onSubmit={this.onSubmit}>
          <label>
            Radius:{' '}
            <input
              type='name'
              onChange={this.handleChange.bind(this)}
              value={this.state.radius}
            />
          </label>
          <Button
            action={this.handleFormSubmit}
            type={'primary'}
            title={'Calculate'}
            style={buttonStyle}
          />{' '}
          {/* Submit */}
          {error ? (
            <div>
              <div>
                <b>Error: </b>
                {error.businessMessage}
              </div>
              <div>
                <b>Error Message:</b> {error.childError[0].developerMessage}
                <br />
              </div>
              <div>{error.status}</div>
            </div>
          ) : (
            <div>
              <table className='table'>
                <thead>
                  <tr>
                    <th style={{ height: '20px' }}>Radius</th>
                    <th style={{ height: '20px' }}>Surface Area</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td style={{ backgroundColor: 'white' }}>{data.radius}</td>
                    <td style={{ backgroundColor: 'white' }}>{data.area}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          )}
        </form>{' '}
      </div>
    )
  }
}

const buttonStyle = {
  margin: '10px 5px 10px 5px'
}

export default CalculateCircleArea
