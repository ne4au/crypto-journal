import { FC, RefObject } from 'react';
import SigningInput from './SigningInput';
import SigningButton from './SigningButton';

interface Props {
    firstFieldRef: RefObject<HTMLInputElement>,
    classNameStyle?: string
}

const SigningInForm: FC<Props>  = ({
    firstFieldRef,
    classNameStyle,
}) => {
    return (
        <div className={`flex flex-col ${classNameStyle}`}>
            <SigningInput
                inputReference={firstFieldRef}
                name='email'
                label='Email' />
            <SigningInput
                name='password'
                label='Password'
                type='password' />
     <div className='flex flex-row-reverse px-5'>
                <SigningButton
                buttonText={'Register'}
                />

            </div>
        </div>);
}

export default SigningInForm;