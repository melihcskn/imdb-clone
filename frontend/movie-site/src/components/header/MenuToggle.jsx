import 'bootstrap-icons/font/bootstrap-icons.css'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import styles from './header.module.css'

const MenuListIcon = styled.i.attrs({
  className: 'bi bi-list',
})`
  color: white;
  font-size: 1.2rem;
  margin-bottom: 0.1rem;
`

export default function MenuToggle() {
  const navigate = useNavigate()

  //Handle menu button click
  const handleMenu = () => {
    navigate('/')
  }

  return (
    <button
      className={`${styles.header__button} ${styles.header__item}`}
      onClick={handleMenu}
    >
      <MenuListIcon />
      <h1>Menu</h1>
    </button>
  )
}
