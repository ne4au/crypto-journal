import { Dispatch, FC, MouseEventHandler, RefObject } from 'react';
import SigningType from './SigningType';
import { UnknownAction } from '@reduxjs/toolkit';

interface Props {
    buttonRef: RefObject<HTMLButtonElement>,
    tabIndex: number,
    clickListener: () => void,
    text: string
}

const SigningFormChangeButton: FC<Props> = ({
    buttonRef,
    tabIndex,
    clickListener,
    text
}) => (
    <button
        ref={buttonRef}
        onClick={() => clickListener()}
        
        tabIndex={tabIndex}
    >
        <div className=' bg-[rgba(17,16,29,0.8)] py-2 px-4 rounded-xl hover:underline underline-offset-[6px]'>
            {text}
        </div>
    </button>
)

export default SigningFormChangeButton;