import { useRef, useState } from 'react'
import 'bootstrap/dist/css/bootstrap.css'

export default function DropdrownButton({
  options,
  handleSelect,
  selectedOption,
  styles,
  type,
}) {
  const [dropdown, setDropdown] = useState(false)

  const isHoveringDropdown = useRef(false)

  //Close dropdown when mouse is out of component
  const onMouseLeave = () => {
    isHoveringDropdown.current = false
    setTimeout(() => {
      if (!isHoveringDropdown.current) {
        setDropdown(false)
        isHoveringDropdown.current = false
      }
    }, 100)
  }

  //Switch dropdown state
  const handleDropdown = (e) => {
    setDropdown(!dropdown)
  }

  //Set option
  const handleOnClick = (e) => {
    handleSelect(e)
    setDropdown(false)
  }

  return (
    <div className={styles[type]}>
      <label className={styles.dropdown} onMouseLeave={onMouseLeave}>
        <div className={styles.dd__button}>{selectedOption}</div>
        <input
          type="checkbox"
          className={styles.dd__input}
          id="test"
          checked={dropdown}
          onChange={handleDropdown}
        />

        <ul
          className={styles.dd__menu}
          onMouseEnter={(e) => {
            isHoveringDropdown.current = true
          }}
        >
          {options.map((item, index) => {
            return (
              <li key={index}>
                {/*List options*/}
                <button
                  id={index}
                  onClick={handleOnClick}
                  value="filters"
                  className={styles.dd__menu__button}
                >
                  {item.name}
                </button>
              </li>
            )
          })}
        </ul>
      </label>
    </div>
  )
}
