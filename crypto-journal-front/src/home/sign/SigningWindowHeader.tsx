import { Transition } from '@headlessui/react';
import { FC } from 'react';
import SigningType from './SigningType';

interface Props {
    activeType: SigningType
}

const SigningWindowHeader: FC<Props> = ({
    activeType,
}) => (<div
    className='flex flex-col text-center content-center items-center justify-center 
    font-poppins font-semibold text-[24px] text-slate-50 py-4 bg-[#000A0A] rounded-t-lg'
>
    <div className='h-[34px]' />
    <Transition
        show={activeType === SigningType.SIGNING_UP}
        enter='transform transition ease-in-out duration-[250ms]'
        enterFrom='-translate-x-full scale-0'
        enterTo='translate-x-0 scale-100'
        leave='transform transition ease-in-out duration-[250ms]'
        leaveFrom='translate-x-0 scale-100'
        leaveTo='-translate-x-full scale-0'
    >
        <span className='flex -translate-y-9 h-0'>
            Registration
        </span>
    </Transition>
    <Transition
        show={activeType === SigningType.SIGNING_IN}
        enter='transform transition ease-in-out duration-[250ms]'
        enterFrom='translate-x-full scale-0'
        enterTo='translate-x-0 scale-100'
        leave='transform transition ease-in-out duration-[250ms]'
        leaveFrom='translate-x-0 scale-100'
        leaveTo='translate-x-full scale-0'
    >
        <span className='flex -translate-y-9 h-0'>
            Authentication
        </span>
    </Transition>
</div>);

export default SigningWindowHeader;